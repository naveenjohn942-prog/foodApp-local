import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Route, Routes, useNavigate } from 'react-router-dom';
import RegisterPage from './component/Register';
import InventoryList from './component/InventoryList';
import LandingPage from './component/index';
import './index.css';
import Home from './component/Home';
import { DataProvider } from './context/DataContext';
import Login from './component/Login';
import Cart from './component/Cart';
import Navbar from './component/nav';
import OrderHistory from './component/OrderHistory/OrderHistory';

function App() {
  // const [isToken, setToken] = useState(null);

  // useEffect(() => {
  //   const token = localStorage.getItem('token');
  //   if (token) {
  //     setToken(true);  // Token is present
  //   } else {
  //     setToken(false); // Token is not present
  //   }
  // }, []);

  return (
    <Router>
      <DataProvider>
        <div>
          <Navbar />
          <Routes>
            <Route path="/" element={<LandingPage  />} />
            <Route path="/inventory" element={<InventoryList />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/login" element={<Login  />} />
            <Route path="/home" element={<Home />} />
            <Route path="/cart" element={<Cart />} />
            <Route path="/history" element={<OrderHistory  />} />
          </Routes>
        </div>
      </DataProvider>
    </Router>
  );
}

export default App;
