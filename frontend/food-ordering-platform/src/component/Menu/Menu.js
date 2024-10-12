import React, { useEffect, useState } from 'react';
import './Menu.css';
import MenuBar from '../MenuBar/MenuBar';
import axios from 'axios';
import { useData } from '../../context/DataContext';
import MenuCard from '../MenuCard/MenuCard';
import Cart from '../Cart';

const Menu = () => {
  const { data, setData } = useData();
  const { user } = data;
  const [searchTerm, setSearchTerm] = useState('');
  const [categoryName, setCategoryName] = useState('All'); // Default category to "All"
  const userId = localStorage.getItem('userId')



  const getData = async () => {
    try {
      const category = [];
      const response = await axios.get('https://gateway.autone.eu.org/inventory/items', {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`,
        },
      });
      setData({ type: 'GET_PRODUCTS', products: response.data });
      response.data?.forEach((ele) => {
        category.push(ele.category);
      });
      setData({ type: 'GET_CATEGORY', category: [...new Set(category)] });
    } catch (error) {
      console.error('Error fetching inventory items:', error.message);
      alert('Failed to fetch inventory items. Please try again later.');
    }
  };
  

  useEffect(() => {
    getData();
  }, []);

  return (
    <div className='menu'>
      <MenuBar
        category={data.category}
        setData={setData}
        setSearchTerm={setSearchTerm}
        setCategoryName={setCategoryName} // Pass setCategoryName to MenuBar
      />
      <div className='menu-row'>
        <div className='menu-products-list'>
          <MenuCard
            menuItem={data.products}
            categoryName={categoryName}
            searchTerm={searchTerm}
            userId={userId}
            token={localStorage.getItem('token')}
            setData={setData}
          />
        </div>
        <div className='menu-cart'>
          <Cart />
        </div>
      </div>
    </div>
  );
};

export default Menu;
