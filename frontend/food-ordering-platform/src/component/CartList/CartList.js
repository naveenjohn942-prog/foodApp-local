import React, { useState } from 'react';
import './CartList.css';
import { useData } from '../../context/DataContext';
import { addToCart, removeFromCart, getCart } from '../../api';

const CartList = ({ cart, userId }) => {
  const [checkoutStatus, setCheckoutStatus] = useState(null);
  const [loading, setLoading] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const token = localStorage.getItem("token");

  const { data, setData } = useData();
  const { user } = data;

  const openModal = () => {
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  const calculateTotal = () => {
    return cart.reduce((total, item) => total + item.price * item.quantity, 0);
  };

  const handleCheckout = async (userId, token) => {
    setLoading(true);
    try {
      const response = await fetch(`http://arm.autone.eu.org:9001/cart/checkout?userId=${userId}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        }
      });

      if (response.ok) {
        getCart(data.user.userId, localStorage.getItem("token"), setData);
        alert('Checkout successful! Your order has been placed.');
      } else {
        const errorMessage = await response.text();
        alert(`Checkout failed: ${errorMessage}`);
      }
    } catch (error) {
      alert(`Checkout failed: ${error.message}`);
    } finally {
      setLoading(false);
      closeModal();
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
                <button onClick={() => removeFromCart(userId, item.itemId, token, setData)}>-</button>
              </span>
            </div>
            <p className="cart-list-price">₹{item.price}</p>
          </li>
        ))}
      </ul>
      
      {/* Show Checkout button only if the cart has items */}
      {cart?.length > 0 && (
        <button className="checkout-btn" onClick={openModal} disabled={loading}>
          {loading ? 'Processing...' : 'Checkout'}
        </button>
      )}

      {isModalOpen && (
        <div className="modal-overlay">
          <div className="modal-content">
            <h2>Order Summary</h2>
            <ul>
              {cart?.map((item) => (
                <li key={item.itemId}>
                  {item.itemName} - {item.quantity} x ₹{item.price} = ₹{item.price * item.quantity}
                </li>
              ))}
            </ul>
            <p><strong>Total: ₹{calculateTotal()}</strong></p>

            <div>
              <h3>Select Payment Method:</h3>
              <input type="radio" name="payment" value="credit" defaultChecked /> Credit Card
              <input type="radio" name="payment" value="paypal" /> PayPal
            </div>

            <button onClick={() => handleCheckout(userId, token)} disabled={loading} className="modal-proceed-btn">
              {loading ? 'Processing...' : 'Proceed to Payment'}
            </button>
            <button onClick={closeModal} className="modal-close-btn">Close</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default CartList;
