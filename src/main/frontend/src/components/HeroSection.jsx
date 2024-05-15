import React from 'react';
import Image from 'next/image';
import NavBar from './NavBar';
import Link from 'next/link';
import { ArrowRightEndOnRectangleIcon } from '@heroicons/react/24/outline';


export const HeroSection = () => {

    return (

        <section className=' mx-auto container'>
            <div className="bg-[url('/img/p10.jpg')] bg-cover">
                <div className='bg-[#00000070]'>
                    <div className='bg-[#2c160ab5]'>
                        <NavBar />
                    </div>
                    {/*
                    <hr className='mx-5 md:mx-8 h-3 mt-4' /> */}
                    <div className='grid grid-cols-2 mt-12'>
                        <div
                            className='space-y-8 items-center mt-1 mb-8 transform-cpu transition-colors place-self-center space-x-3 '>
                            <div className=''>
                                <Link href='/menu'
                                    className='text-2xl flex items-center justify-center text-black font-bold shadow-xl border-4 border-black rounded-full py-3 px-10 bg-gradient-to-r  from-yellow-500 to-[#b40606f4] hover:from-amber-500 hover:to-red-600  transition-transform transform hover:scale-105 duration-300'>
                                Full Menu
                                <ArrowRightEndOnRectangleIcon className='w-8 ms-6 font-semibold' />
                                </Link>
                            </div>

                            <div>
                                <Link href='/menu'
                                    className='text-2xl flex items-center justify-center text-black font-bold shadow-xl border-4 border-black rounded-full py-3 px-10 bg-gradient-to-r  from-yellow-500 to-[#b40606f4] hover:from-amber-500 hover:to-red-600  transition-transform transform hover:scale-105 duration-300'>
                                Today Specials
                                <span
                                    className='ml-3 text-xl transform transition-transform duration-300 hover:scale-110'>😉</span>
                                </Link>
                            </div>
                        </div>

                        <div
                            className='mx-auto bg-[url("/img/p11.jpg")] bg-cover  rounded-full items-center mt-1 mb-8 transform-cpu transition-colors place-self-center space-x-3 '>
                            <div className='bg-[#000000e1] rounded-full p-16 '>
                                <div
                                    className='p-5 rounded-xl text-transparent bg-clip-text bg-gradient-to-br from-yellow-400 via-red-500 to-orange-600'>
                                    <h3 className='text-5xl text-center my-4 font-bold'>Welcome to Pizza Creed</h3>
                                    <h3 className='text-2xl text-center my-4'>Where every slice tells a story</h3>
                                    <h3 className='text-3xl text-center my-4'>Indulge in the ultimate pizza experience</h3>
                                    <h3 className='text-2xl text-center my-4'>Crafted with passion, served with love</h3>
                                    <h3 className='text-4xl text-center my-4'>Savor the Flavor</h3>
                                    <h3 className='text-3xl text-center my-4'>Delicious moments await you</h3>
                                    <h3 className='text-5xl text-center my-4'>at your doorstep</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}