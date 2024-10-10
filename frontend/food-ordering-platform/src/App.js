// src/App.js
import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import RegisterPage from './component/Register';
import InventoryList from './component/InventoryList';
import LandingPage from './component/index';
import './index.css';
import Home from './component/Home';
import { DataProvider } from './context/DataContext';
import Login from './component/Login';
import Cart from './component/Cart';
import Navbar from './component/nav';
// import Navbar from './component/navbar';

function App() {
  const [isToken, setToken] = useState(null);
  useEffect(() => {
    const token = localStorage.getItem('token')
  if(token){  
    setToken(true)
  }else{
    setToken(null)
  }
  }, [])
  return (
    <Router>
      <DataProvider>
      <div>
      
        <Routes>
          
        <Route path="/" element={<LandingPage isToken={isToken} />} />
        
          <Route path="/inventory" element={<InventoryList />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<Login isToken={isToken} />} />
          <Route path="/Home" element={<Home isToken={isToken}  />} />
          <Route path="/cart" element={<Cart />} />
        </Routes>
      </div>

      </DataProvider>

    </Router>
  );
}

export default App;
