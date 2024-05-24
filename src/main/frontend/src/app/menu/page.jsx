'use client';
import React, { useState, useEffect } from 'react';
import Footer from '@/components/Footer';
import { useRouter } from 'next/navigation';
import Image from 'next/image';
import { PlusIcon } from '@heroicons/react/24/outline';

export default function ProductListing() {
    const router = useRouter();
    const [pizzas, setPizza] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [selectedOptions, setSelectedOptions] = useState({});

    // Fetch Pizza list
    useEffect(() => {
        fetchPizzaMenu();
    }, []);

    const fetchPizzaMenu = () => {
        fetch('https://x8c0cgkj-8080.asse.devtunnels.ms/api/pizzacreed/pizza/menu')
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Failed to fetch pizzas');
                }
                return response.json();
            })
            .then(data => {
                if (data.content) {
                    setPizza(data.content);
                } else {
                    setError("Failed to load pizzas: " + (data.message || "Unknown Error!!!"));
                }
            })
            .catch(err => {
                setError(err.message);
            })
            .finally(() => {
                setLoading(false);
            });
    };

    const handleOptionChange = (pizzaId, optionType, value) => {
        setSelectedOptions(prevOptions => ({
          ...prevOptions,
          [pizzaId]: {
            ...prevOptions[pizzaId],
            [optionType]: value
          }
        }));
    };
    
    const getSelectedPrice = (pizzaId) => {
        const selectedSizeId = selectedOptions[pizzaId]?.size;
        const selectedPizza = pizzas.find(pizza => pizza.pizzaId === pizzaId);
        const selectedSize = selectedPizza?.sizes.find(size => size.sizeId.toString() === selectedSizeId);
        return selectedSize ? selectedSize.price : null;
    };

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error}</p>;

    return (
        <div>
            <div className="container py-12 px-8 ">
                <div className='grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4'>
                {pizzas.map(pizza => (
                    <div
                    key={pizza.pizzaId}
                    className="bg-[radial-gradient(ellipse_at_top_right,_var(--tw-gradient-stops))] from-[#000315] via-[#150600] to-black rounded-lg shadow-md flex flex-col justify-center items-center mb-6">
                    {/* Pizza Image */}
                    <img src={`img/${pizza.pizzaImg}`} alt={`${pizza.pizzaName}`} className="w-full h-42 rounded-t-lg object-cover" />

                    <div className="pt-4 text-center">
                        {/* Pizza Title */}
                        <h3 className="text-xl text-orange-200 font-semibold mb-2">{pizza.pizzaName}</h3>
                        {/* Pizza Description */}
                        <h3 className="text-sm text-orange-100 font-thin text-justify mb-2 mx-6">{pizza.pizzaDiscription}</h3>

                        <p className="text-orange-400 mb-4">Choose your crust</p>
                        {/* Crust Selection Dropdown */}
                        <select
                        value={selectedOptions[pizza.pizzaId]?.crust || ''}
                        onChange={(e) => handleOptionChange(pizza.pizzaId, 'crust', e.target.value)}
                        className="mb-4 w-3/4 px-4 py-1  rounded-2xl border-2 border-orange-500 text-orange-900"
                        >
                        <option className='bg-orange-300 mx-10' value="">Select Crust</option>
                        <option className='bg-orange-300' value="Pan">Pan</option>
                        <option className='bg-orange-300' value="Sausage">Sausage</option>
                        </select>

                        <p className="text-orange-400 mb-4">Choose Size</p>
                        {/* Size Selection Dropdown */}
                        <select
                        value={selectedOptions[pizza.pizzaId]?.size || ''}
                        onChange={(e) => handleOptionChange(pizza.pizzaId, 'size', e.target.value)}
                        className="mb-4 w-3/4 px-4 py-1 rounded-2xl border-2 border-orange-500 text-orange-900"
                        >
                        <option className='bg-orange-300' value="">Select Size</option>
                        {pizza.sizes.map(size => (
                            <option key={size.sizeId} className='bg-orange-300' value={size.sizeId}>
                            {size.sizeName} - Rs.{size.price}
                            </option>
                        ))}
                        </select>

                        {/* Display selected size price
                        <p className="text-orange-800 font-semibold">
                        {getSelectedPrice(pizza.pizzaId) && `${selectedOptions[pizza.pizzaId]?.sizeName} - Rs.${getSelectedPrice(pizza.pizzaId)}`}
                        </p> */}

                        {/* Order Button */}
                        <div className='flex justify-center mt-6 mb-5'>
                            <button className="flex justify-center mb-4 w-4/5 border-2 border-orange-500  bg-orange-500 text-white py-2 px-8 rounded-3xl hover:bg-orange-600 transition duration-300">
                            Add to bucket <PlusIcon className='ml-2 h-5 font-semibold text-white' />
                            </button>
                        </div>
                    </div>
                    </div>
                ))}
                </div>
            </div>
            <Footer />
        </div>
    );
}
