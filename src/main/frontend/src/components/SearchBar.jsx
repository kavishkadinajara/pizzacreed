import React from 'react'

export default function SearchBar() {
  return (
    <div>
        <div className="relative">
          <input 
            type="text" 
            className="py-2 px-4 bg-gray-800 rounded-lg placeholder-gray-400 text-sm focus:outline-none focus:ring-2 focus:ring-green-400" 
            placeholder="Search..." 
          />
          <button className="absolute inset-y-0 right-0 flex items-center px-4 bg-green-400 rounded-r-lg">
            <svg className="w-5 h-5 text-gray-800" viewBox="0 0 20 20" fill="currentColor">
              <path fillRule="evenodd" d="M13.5 11a4.5 4.5 0 1 0-9 0 4.5 4.5 0 0 0 9 0zM18 17a1 1 0 0 1-1 1h-3.1a8 8 0 1 1-1.19-1.19V14a1 1 0 0 1 2 0v2.1c.36.1.69.29 1 .52V14a1 1 0 0 1 2 0v4a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1v-4a1 1 0 1 1 2 0v2.53A9.98 9.98 0 0 1 9.47 13H5a1 1 0 0 1-1-1V9a1 1 0 1 1 2 0v2h10V9a1 1 0 0 1 2 0v7z" clipRule="evenodd" />
            </svg>
          </button>
        </div>
    </div>
  )
}
