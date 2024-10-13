import React, { useEffect, useState } from "react";
import axios from "axios";
import "./OrderHistory.css";
import { useData } from "../../context/DataContext";
import Navbar from "../nav";

const OrderHistory = ({isToken}) => {
  const [orders, setOrders] = useState([]);
  const { data, setData } = useData();
  const userId = data.user.userId;
  const token = localStorage.getItem("token");

  useEffect(() => {
    // Fetch order history data from API
    axios
      .get(`https://gateway.autone.eu.org/orders/history?userId=${userId}`, {
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`, // Pass token here
        },
      })
      .then((response) => {
        setOrders(response.data);
      })
      .catch((error) => {
        console.error("Error fetching order history:", error);
      });
  }, []);

  return (<>
  <div className="order-history-container">
      <h2 className="order-history-title">Order History</h2>
      <div className="order-history-grid">
        {orders.map((order) => (
          <div className="order-card" key={order.id}>
            <div className={`order-status ${order.status.toLowerCase()}`}>
              {order.status}
            </div>
            <h3>Order #{order.id}</h3>
            <p>
              <strong>Tracking ID:</strong> {order.trackingId}
            </p>
            <p>
              <strong>Date:</strong>{" "}
              {new Date(order.createdAt).toLocaleString()}
            </p>

            <div className="order-items">
              <h4>Items</h4>
              <ul>
                {order.orderItems.map((item) => (
                  <li key={item.id}>
                    <span>
                      {item.quantity} x Item {item.itemId}
                    </span>
                    <span>₹{item.price.toFixed(2)}</span>
                  </li>
                ))}
              </ul>
            </div>
          </div>
        ))}
      </div>
    </div>
  </>
    
  );
};

export default OrderHistory;
