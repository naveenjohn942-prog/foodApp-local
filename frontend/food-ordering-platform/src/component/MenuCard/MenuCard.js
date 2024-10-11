import React from 'react';
import './MenuCard.css';
import { addToCart } from '../../api';

const MenuCard = ({ menuItem, categoryName, searchTerm, userId, token, setData }) => {
  const filteredItems = menuItem?.filter((item) => {
    const matchesCategory = categoryName === 'All' || item.category === categoryName;
    const matchesSearchTerm = item.name.toLowerCase().includes(searchTerm.toLowerCase());
    return matchesCategory && matchesSearchTerm;
  });

  return (
    filteredItems.map((item, i) => (
      <div className='menucard' key={i}>
        <img src={item.imageUrl} alt='' className='menucard-img' />
        <h3 className='menucard-name'>{item.name}</h3>
        <p className='menucard-price'> <sup>&#8377;</sup>{item.price}</p>
        <button className='menucard-button' onClick={() => addToCart(userId, item.id, token, setData)}>
          Add to cart
        </button>
      </div>
    ))
  );
};

export default MenuCard;
