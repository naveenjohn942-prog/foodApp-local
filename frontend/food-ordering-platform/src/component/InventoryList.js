import React, { useState, useEffect } from 'react';
import axios from 'axios';

const InventoryList = () => {
  const [items, setItems] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('All');
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchItems(selectedCategory);
  }, [selectedCategory]);

  const fetchItems = async (category) => {
    setLoading(true);
    try {
      const response = await axios.get(category === 'All'
        ? 'http://localhost:9000/inventory/items'
        : `http://localhost:9000/inventory/items/category/${category}`);
      setItems(response.data);
    } catch (error) {
      console.error('Error fetching items:', error);
    }
    setLoading(false);
  };

  return (
    <div>
      <h1>Inventory</h1>
      <div>
        <button onClick={() => setSelectedCategory('All')}>All</button>
        <button onClick={() => setSelectedCategory('Fruits')}>Fruits</button>
        <button onClick={() => setSelectedCategory('Vegetable')}>Vegetables</button>
        <button onClick={() => setSelectedCategory('Non-veg')}>Non-Veg</button>
        <button onClick={() => setSelectedCategory('Breads')}>Breads</button>
      </div>

      {loading ? (
        <p>Loading items...</p>
      ) : (
        <div style={{ display: 'flex', flexWrap: 'wrap' }}>
          {items.length > 0 ? (
            items.map((item) => (
              <div key={item.id} style={{ margin: '10px', border: '1px solid #ccc', padding: '10px', width: '200px' }}>
                <img src={item.image} alt={item.name} style={{ width: '100%' }} />
                <h3>{item.name}</h3>
                <p>Price: ${item.price}</p>
                <p>Stock: {item.stock}</p>
              </div>
            ))
          ) : (
            <p>No items available</p>
          )}
        </div>
      )}
    </div>
  );
};

export default InventoryList;
