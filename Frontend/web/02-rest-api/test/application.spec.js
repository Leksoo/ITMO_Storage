"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const tslib_1 = require("tslib");
/* eslint-disable @typescript-eslint/no-explicit-any */
const supertest_1 = tslib_1.__importDefault(require("supertest"));
const app_1 = tslib_1.__importDefault(require("../src/app"));
const db_1 = tslib_1.__importDefault(require("../src/db"));
const request = supertest_1.default(app_1.default);
const cleanDb = async () => {
    const tableNames = Object.keys(db_1.default.sequelize.models);
    await db_1.default.sequelize.query(`TRUNCATE ${tableNames.map(name => `"${name}"`).join(', ')} RESTART IDENTITY CASCADE;`);
};
describe('API', () => {
    beforeAll(async () => {
        // Здесь создаются все таблицы по моделям
        await db_1.default.sequelize.sync({ force: true });
    });
    beforeEach(async () => {
        cleanDb();
    });
    it('Должно вернуть список мест', async () => {
        const obj1 = {
            name: 'New York',
            description: 'nice'
        };
        const obj2 = {
            name: 'London',
            description: 'well'
        };
        await request.post('/locations').send(obj1);
        await request.post('/locations').send(obj2);
        let res = await request.get('/locations');
        expect(res.status).toBe(200);
        expect(res.body[0]).toMatchObject(obj1);
        expect(res.body[1]).toMatchObject(obj2);
        res = await request.get('/locations?order=name');
        expect(res.status).toBe(200);
        expect(res.body[0]).toMatchObject(obj2);
        expect(res.body[1]).toMatchObject(obj1);
        res = await request.get('/locations?order=time');
        expect(res.status).toBe(200);
        expect(res.body[0]).toMatchObject(obj1);
        expect(res.body[1]).toMatchObject(obj2);
    });
    it('Должно создать место', async () => {
        const resPost = await request.post('/locations').send({
            name: 'test name',
            description: 'test description'
        });
        const resGet = await request.get('/locations');
        const expectedPartResponse = {
            name: 'test name',
            description: 'test description',
            visited: false
        };
        expect(resPost.status).toBe(204);
        expect(resGet.body[0]).toMatchObject(expectedPartResponse);
    });
    it('Должно вернуть место по id', async () => {
        await request.post('/locations').send({
            name: 'test name by id',
            description: 'test description by id'
        });
        const resGet = await request.get('/locations/1');
        const expectedPartResponse = {
            name: 'test name by id',
            description: 'test description by id',
            visited: false
        };
        expect(resGet.status).toBe(200);
        expect(resGet.body).toMatchObject(expectedPartResponse);
    });
    it('Должно удалить место по id', async () => {
        await request.post('/locations').send({
            name: 'test name by id',
            description: 'test description by id'
        });
        const resDelete = await request.delete('/locations/1');
        const resGetAfter = await request.get('/locations/1');
        expect(resDelete.status).toBe(204);
        expect(resGetAfter.status).toBe(404);
    });
    it('Должно изменить поля', async () => {
        await request.post('/locations').send({
            name: 'test name',
            description: 'test description'
        });
        const resChange = await request.post('/locations/1').send({
            description: 'new description'
        });
        const resChange2 = await request.patch('/locations/1?visited=true');
        const resGetAfter = await request.get('/locations/1');
        const expectedPartResponse = {
            name: 'test name',
            description: 'new description',
            visited: true
        };
        expect(resChange.status).toBe(204);
        expect(resChange2.status).toBe(204);
        expect(resGetAfter.body).toMatchObject(expectedPartResponse);
    });
    it('Должно вернуться постранично', async () => {
        const obj = {
            name: 'test name',
            description: 'test description'
        };
        for (let i = 0; i < 10; i++) {
            await request.post('/locations').send(obj);
        }
        const resGet = await request.get('/locations/pages?size=3');
        expect(resGet.status).toBe(200);
        expect(resGet.body).toHaveLength(4);
        expect(resGet.body[0]).toHaveLength(3);
        expect(resGet.body[1]).toHaveLength(3);
        expect(resGet.body[2]).toHaveLength(3);
        expect(resGet.body[3]).toHaveLength(1);
    });
    it('Должно удалить все', async () => {
        const obj = {
            name: 'test name',
            description: 'test description'
        };
        for (let i = 0; i < 10; i++) {
            await request.post('/locations').send(obj);
        }
        await request.delete('/locations');
        const resGet = await request.get('/locations');
        expect(resGet.body).toHaveLength(0);
    });
    it('Должно найти по описанию', async () => {
        for (let i = 0; i < 10; i++) {
            const obj = {
                name: `obj${i}`,
                description: `desc${i}`
            };
            await request.post('/locations').send(obj);
        }
        const resGet = await request.get('/locations/search/desc4');
        expect(resGet.body).toHaveLength(1);
        expect(resGet.body[0].name).toBe('obj4');
    });
});
