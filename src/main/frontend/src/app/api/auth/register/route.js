import { hash } from 'bcrypt';
import { query } from '@/lib/db';
import { NextResponse } from 'next/server';

export async function POST(request) {
  try {
    const { customerName, customerAddress, customerTele, customerEmail, password } = await request.json();

    // Validate customerAddress and password if needed

    console.log({ customerName, customerAddress, customerEmail });

    const hashedPassword = await hash(password, 10);

    const response1 = await query({
      query: `
        INSERT INTO customer (customer_name, customer_email, customer_tele, password, customer_address)
        VALUES (?, ?, ?, ?, ?)
      `,
      values: [customerName, customerEmail, customerTele, hashedPassword, customerAddress]
    });

    console.log("res1", response1);

    const result1 = {
      insertId: response1.insertId,
      affectedRows: response1.affectedRows
    };

    const response2 = await query({
      query: `
        INSERT INTO shopping_basket (customer_id, total_amount)
        VALUES (?, ?)
      `,
      values: [result1.insertId, 0.0]
    });

    console.log("res2", response2);

    const result2 = {
      insertId: response2.insertId,
      affectedRows: response2.affectedRows
    };

    console.log("result1", result1);
    console.log("result2", result2);

    return NextResponse.json({ message: 'success', ...result1 , ...result2});
  } catch (error) {
    console.error("Error:", error);
    return NextResponse.json({ message: 'error' });
  }
}
