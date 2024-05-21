'use client';
import React, { useState, useEffect } from 'react';
import Footer from '@/components/Footer';
import { useRouter } from "next/navigation";
import Image from 'next/image';
import { PlusIcon } from '@heroicons/react/24/outline';

export default function ProductListing() {
    const router = useRouter();
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Fetch program list
    useEffect(() => {
        fetch('http://localhost:8080/api/pizzacreed/pizza/menu')
        .then((response) => {
                if (!response.ok) {
                throw new Error('failed to fetch programs');
                }
                return response.json();
            })
            .then((data) => {
            console.log(data);
            if (!data) {
                //toast.error('Check your connection !');
                return;
            }
            // const newProgramList = data.map((Program) => ({
            // value: Program.web_value,
            // label: Program.name,
            // }));
            setProducts(data[0]);

           });
    }, []);


    return (
    <div>
        <div className="container py-12 px-8 ">
            <div className='grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4'>
                {/* {products.map(product => (
                <div key={product.id}
                    className="bg-[radial-gradient(ellipse_at_top_right,_var(--tw-gradient-stops))] from-[#000315] via-[#001515] to-black rounded-lg shadow-md flex flex-col justify-center items-center">
                    <img src={product.image} alt={product.name} className="w-full  object-cover rounded-t-lg" />
                    <div className="p-6 text-center">
                        <h3 className="text-xl text-orange-200 font-semibold mb-2">{product.name}</h3>
                        <p className="text-orange-400 mb-4">{product.description}</p>
                        <p className="text-orange-800 font-semibold">${product.price}</p>
                        <button
                            className="mt-4 bg-orange-500 text-white py-2 px-4 rounded hover:bg-orange-600 transition duration-300">
                            Order Now
                        </button>
                    </div>
                </div>
                ))} */}

                <div
                    className="bg-[radial-gradient(ellipse_at_top_right,_var(--tw-gradient-stops))] from-[#000315] via-[#150600] to-black rounded-lg shadow-md flex flex-col justify-center items-center">
                    {/* Pizza Image */}
                    <img src={'/img/p2.png'} alt={'pizza'} className="w-full rounded-t-lg object-cover" />

                    <div className=" pt-4 text-center">
                        {/* Pizza Title */}
                        <h3 className="text-xl text-orange-200 font-semibold mb-2">Pizza</h3>
                        {/* Pizza Discription */}
                        <h3 className="text-md text-orange-100 font-thin text-justify mb-2">Pizza</h3>

                        <p className="text-orange-400 mb-4">Choose your crust</p>
                        {/* Size Selection Dropdown */}
                        <select value={2} onChange={'handleSizeChange'}
                            className="mb-4 w-full px-4 py-1 rounded-2xl border-2 border-orange-500 text-orange-900">
                            <option className='bg-orange-300' value="small">Pan</option>
                            <option className='bg-orange-300' value="medium">Sausage</option>
                        </select>

                        <p className="text-orange-400 mb-4">Choose Size</p>
                        {/* Size Selection Dropdown */}
                        <select value={2} onChange={'handleSizeChange'}
                            className="mb-4 w-full px-4 py-1 rounded-2xl border-2 border-orange-500 text-orange-900">
                            <option className='bg-orange-300' value="small">Small - Rs.1000</option>
                            <option className='bg-orange-300' value="medium">Medium - Rs.1500</option>
                            <option className='bg-orange-300' value="large">Large - Rs.2200</option>
                        </select>

                        {/* Display selected size price (if needed, currently empty) */}
                        <p className="text-orange-800 font-semibold">{/* Optionally display selected size price here */}</p>

                        {/* Order Button */}
                        <button className="my-4 flex items-center justify-center bg-orange-500 text-white py-2 px-8 rounded-3xl hover:bg-orange-600 transition duration-300">
                            Add to bucket <PlusIcon className='ml-2 w-5 h-5 font-semibold text-white' />
                        </button>
                    </div>
                    
                </div>
                <div
                    className="bg-[radial-gradient(ellipse_at_top_right,_var(--tw-gradient-stops))] from-[#000315] via-[#1e0d00] to-black rounded-lg shadow-md flex flex-col justify-center items-center">
                    {/* Pizza Image */}
                    <img src={'/img/p1.png'} alt={'pizza'} className="w-full rounded-t-lg object-cover" />

                    <div className=" pt-4 text-center">
                        {/* Pizza Title */}
                        <h3 className="text-xl text-orange-200 font-semibold mb-2">Pizza</h3>
                        {/* Pizza Discription */}
                        <h3 className="text-md text-orange-100 font-thin text-justify mb-2">Pizza</h3>

                        <p className="text-orange-400 mb-4">Choose your crust</p>
                        {/* Size Selection Dropdown */}
                        <select value={2} onChange={'handleSizeChange'}
                            className="mb-4 w-full px-4 py-1 rounded-2xl border-2 border-orange-500 text-orange-900">
                            <option className='bg-orange-300' value="small">Pan</option>
                            <option className='bg-orange-300' value="medium">Sausage</option>
                        </select>

                        <p className="text-orange-400 mb-4">Choose Size</p>
                        {/* Size Selection Dropdown */}
                        <select value={2} onChange={'handleSizeChange'}
                            className="mb-4 w-full px-4 py-1 rounded-2xl border-2 border-orange-500 text-orange-900">
                            <option className='bg-orange-300' value="small">Small - Rs.1000</option>
                            <option className='bg-orange-300' value="medium">Medium - Rs.1500</option>
                            <option className='bg-orange-300' value="large">Large - Rs.2200</option>
                        </select>

                        {/* Display selected size price (if needed, currently empty) */}
                        <p className="text-orange-800 font-semibold">{/* Optionally display selected size price here */}</p>

                        {/* Order Button */}
                        <button className="my-4 flex items-center justify-center bg-orange-500 text-white py-2 px-8 rounded-3xl hover:bg-orange-600 transition duration-300">
                            Add to bucket <PlusIcon className='ml-2 w-5 h-5 font-semibold text-white' />
                        </button>
                    </div>
                    
                </div>

            </div>
        </div>
        <Footer />
    </div>
    );
}