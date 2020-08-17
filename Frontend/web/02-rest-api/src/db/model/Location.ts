import { DataTypes, Model } from 'sequelize';

import db from '../index';

class Location extends Model {
  public id!: number;
  public name!: string;
  public description!: string;
  public country!: string | null;
  public city!: string | null;
  public visited!: boolean;

  public readonly createdAt!: Date;
  public readonly updatedAt!: Date;
}

Location.init(
  {
    id: {
      type: DataTypes.INTEGER,
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    description: {
      type: DataTypes.STRING,
      allowNull: false
    },
    country: {
      type: DataTypes.STRING,
      allowNull: true
    },
    city: {
      type: DataTypes.STRING,
      allowNull: true
    },
    visited: {
      type: DataTypes.BOOLEAN,
      defaultValue: false,
      allowNull: false
    }
  },
  {
    sequelize: db.sequelize,
    tableName: 'Location'
  }
);

export default Location;
