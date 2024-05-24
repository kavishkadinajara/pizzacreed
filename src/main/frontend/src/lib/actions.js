'use server'

import { revalidatePath } from "next/cache";
import { query } from "./db";
import ReviewModel from "@/models/ReviewModel";
import { dbConnect } from "./mongodb";


export async function SubmitToCart(qty, productId, email, formData) {
    try {
        if (!email) {
            return {
                status: 401,
                message: null,
                error: "You must be logged in to add to cart."
            }
        }
        const res = await query({
            query: "CALL addToCart(?, ?, ?);",
            values: [productId, email, qty],
        });

        console.log(res.affectedRows)

        console.log(`Adding ${qty} of ${productId} to cart`);
        revalidatePath('/cart');
        return {
            status: 200,
            message: "Product added to cart",
            error: null
        }
    } catch (error) {
        console.log(error)
        return {
            status: 500,
            message: null,
            error: "Error adding to cart"
        }
    }

}

export async function removeReview(data, state) {
    "use server"
    try {
        console.log(data)
        console.log(state)
        const { productId, reviewId } = data;
        await dbConnect();
        const res = await ReviewModel.findByIdAndDelete(reviewId);
        // console.log(res)
        console.log("deleted: ", reviewId, "of ", productId);
        revalidatePath(`/product/${productId}`);
    } catch (error) {
        console.log(error.message)
    }

}