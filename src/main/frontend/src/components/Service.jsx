import React from 'react'
import Image from 'next/image'

export default function Service() {
  return (
    <div id='service-area'>
        <div className='min-h-screen'>
                <div className='grid grid-cols-3'>
                    <div className='flex justify-center mt-28 col-span-2'>
                        <div>
                            <h1 className='text-center mb-8 text-3xl shadow-lg  text-orange-300'>Our Delivery Service Area</h1>
                            <Image 
                            src={"/img/map-area.jpg"}
                            width={800}
                            height={400} 
                            className='rounded-2xl shadow-lg shadow-orange-700'
                        />
                        </div>
                    </div>
                    <div className='flex justify-center mt-28'>
                        <Image 
                            src={"/logos/logo-2.png"}
                            width={440}
                            height={440} 
                            className=''
                        />
                    </div>

                </div>

            </div>
    </div>
  )
}
