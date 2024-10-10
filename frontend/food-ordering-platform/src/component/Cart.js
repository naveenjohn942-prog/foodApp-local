import React, { useEffect } from 'react'
import CartList from './CartList/CartList'
import { useData } from '../context/DataContext'
import { getCart } from '../api'

const Cart = () => {
const {data, setData} = useData()
useEffect(() => {
    getCart(data.user.userId, localStorage.getItem("token"), setData)
},[])
  return (
    <div className='cart'>
    <div className='cart-header'>
    <h1>Cart</h1> 
    <span>{data.user.name}</span>
    </div>
    <CartList cart={data.cart} userId={data.user.userId} />
      
    </div>
  )
}

export default Cart
