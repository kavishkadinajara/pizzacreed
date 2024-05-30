import mysql from "mysql2/promise";

export async function query({ query, values = [] }) {
  const dbconnection = await mysql.createConnection({
    host: "****************",
    database: "*********************",
    user: "***************",
    password: "*********************",
  });

  try {
    const [results] = await dbconnection.execute(query, values);
    return results;
  } catch (error) {
    throw new Error(error.message);
  } finally {
    await dbconnection.end();
  }
}
