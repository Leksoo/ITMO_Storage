import { Action, AnyState, Reducer, Store, StoreCreator, Unsubscribe } from './types';

const createStore: StoreCreator = <S, A extends Action>(
  reducer: Reducer<S, A>,
  preloadedState?: S,
  enhancer?: any
): Store<S, A> => {
  if (typeof enhancer === 'undefined' && typeof preloadedState === 'function') {
    enhancer = preloadedState;
    preloadedState = undefined;
  }
  if (typeof enhancer !== 'function' && typeof enhancer !== 'undefined') {
    throw new Error('enhancer is neither undefined nor a function');
  }
  if (typeof reducer !== 'function') {
    throw new Error('reducer is not a function');
  }
  if (enhancer) {
    return enhancer(createStore)(reducer, preloadedState);
  }
  let state = preloadedState || reducer(preloadedState, { type: undefined } as A);
  const listeners = new Map<number, () => void>();
  let listenerId = 0;

  return {
    dispatch(action: A): void {
      if (typeof action.type === 'undefined') {
        throw new Error('Actions may not have an undefined "type" property');
      }
      state = reducer(state, action);
      [...listeners.values()].forEach(value => value());
    },
    getState(): S {
      return state;
    },
    subscribe(listener: () => void): Unsubscribe {
      if (typeof listener !== 'function') {
        throw new Error('listener must be a function');
      }
      const curId = listenerId++;
      listeners.set(curId, listener);

      return (): void => {
        listeners.delete(curId);
      };
    }
  };
};

const applyMiddleware = (...middlewares: any[]) => (storeCreator: StoreCreator) => (
  reducer: Reducer,
  state?: AnyState
) => {
  let dispatch = (_: any): any => {
    throw new Error('Dispatch while preparing middleware');
  };
  const store = storeCreator(reducer, state);
  const mwStore = {
    getState: store.getState,
    dispatch: (action: any) => dispatch(action)
  };

  const functions = middlewares.map(m => m(mwStore));
  dispatch = functions.reduce((acc, cur) => (m: any): any => acc(cur(m)))(store.dispatch);

  return {
    ...store,
    dispatch
  };
};

export { createStore, applyMiddleware };
