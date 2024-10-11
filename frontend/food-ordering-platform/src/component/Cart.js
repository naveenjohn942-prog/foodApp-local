import React, { useEffect } from 'react'
import CartList from './CartList/CartList'
import { useData } from '../context/DataContext'
import { getCart } from '../api'

const Cart = () => {
const {data, setData} = useData()
const userId = localStorage.getItem('userId')
useEffect(() => {
    getCart(userId, localStorage.getItem("token"), setData)
},[])
  return (
    <div className='cart'>
    <div className='cart-header'>
    <h1>Cart</h1> 
    </div>
    <CartList cart={data.cart} userId={userId} />
      
    </div>
  )
}

export default Cart
