// import axios from "axios";

// // Set your backend API base URL here
// const API_BASE_URL = "http://localhost:8080"; // Example URL

// export const login = (credentials) => axios.post(`${API_BASE_URL}/auth/login`, credentials);
// export const register = (data) => axios.post(`${API_BASE_URL}/auth/register`, data);
// export const getInventory = () => axios.get(`${API_BASE_URL}/inventory/items`);
// export const checkStock = (itemId, quantity) => axios.get(`${API_BASE_URL}/inventory/items/checkStock`, { params: { itemId, quantity } });
// export const placeOrder = (orderData) => axios.post(`${API_BASE_URL}/orders`, orderData);
// export const getOrderHistory = (userId) => axios.get(`${API_BASE_URL}/orders/history`, { params: { userId } });


import axios from "axios";


export const addToCart = async (userId, itemId,token, setData) => {
 
    console.log(token)
const response = await axios.post(`https://gateway.autone.eu.org/cart/add?userId=${userId}&itemId=${itemId}&quantity=1`, {},
    {      headers: {
        'Authorization': `Bearer ${token}`,}}
    )
    getCart(userId, token, setData)
console.log(response)   
}

export const getCart = async (userId, token, setData) => {
    const response = await axios.get(`https://gateway.autone.eu.org/cart/view?userId=${userId}`, {
        headers: { 'Authorization': `Bearer ${token}` }
    });

    // Sorting the items by itemId
    const sortedItems = response.data.items.sort((a, b) => a.itemId - b.itemId);

    // Updating the state with the sorted items
    setData({ type: "CART", cart: sortedItems });
    
    console.log(sortedItems, "Sorted Cart Items");
};


export const removeFromCart = async (userId, itemId,token, setData) => {
 
    console.log(token)
const response = await axios.delete(`https://gateway.autone.eu.org/cart/remove?userId=${userId}&itemId=${itemId}&quantity=1`,
    {      headers: {
        'Authorization': `Bearer ${token}`,}}
    )
    getCart(userId, token, setData)
console.log(response)   
}