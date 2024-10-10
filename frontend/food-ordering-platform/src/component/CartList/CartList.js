import React, { useState } from 'react';
import './CartList.css';
import { useData } from '../../context/DataContext'
import { addToCart } from '../../api'

const   CartList = ({ cart, userId }) => {
  const [checkoutStatus, setCheckoutStatus] = useState(null);
  const [loading, setLoading] = useState(false);
  const token=localStorage.getItem("token")

  console.log('helllll',cart)

  const {data, setData} = useData()
    const {user} = data;

  // Checkout function to trigger API call
  const handleCheckout = async () => {
    setLoading(true);
    try {
      const response = await fetch('http://localhost:9000/cart/checkout', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ userId: 2 }), 
      });

      if (response.ok) {
        const result = await response.json();
        setCheckoutStatus('Checkout successful! Your order has been placed.');
      } else {
        const errorMessage = await response.text();
        setCheckoutStatus(`Checkout failed: ${errorMessage}`);
      }
    } catch (error) {
      setCheckoutStatus(`Checkout failed: ${error.message}`);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="cart-container">
      <ul className="cart-list">
  {cart?.map((item) => (
    <li key={item.itemId} className="cart-list-items">
      <img
        src={item.imageUrl || 'default-image.jpg'}
        alt={item.itemName}
        className="cart-list-image"
      />
      <div className="cart-list-info">
        <p className="cart-list-name">{item.itemName}</p>
        <span className="cart-list-btn">
          <button onClick={() => addToCart(userId, item.itemId, token, setData)}>+</button>
          {item.quantity}
          <button>-</button>
        </span>
      </div>
      <p className="cart-list-price">{item.price}</p>
    </li>
  ))}
</ul>


      {/* Checkout button */}
      <button className="checkout-btn" onClick={handleCheckout} disabled={loading}>
        {loading ? 'Processing...' : 'Checkout'}
      </button>

      {/* Display checkout status */}
      {checkoutStatus && <p className="checkout-status">{checkoutStatus}</p>}
    </div>
  );
};

export default CartList;