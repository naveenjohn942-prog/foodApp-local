import React from 'react'
import "./Navbar.css"
import { Link } from 'react-router-dom'
const Navbar = () => {
  return (
    <div className='navbar'>
      <ul>
        <li className='navbar-items'>menu</li>
       <Link to='/cart'><li className='navbar-items'>cart</li></Link>
      </ul>
    </div>
  )
}

export default Navbar
