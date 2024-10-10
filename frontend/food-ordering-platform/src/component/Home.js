import React from 'react'
import Menu from './Menu/Menu';
import Navbar from './nav';

const Home = (isToken) => {
  return (
    <div>
      <Navbar isToken={isToken}/>
      <Menu />
      
    </div>
  )
}

export default Home;
