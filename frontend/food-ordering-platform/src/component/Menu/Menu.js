import React, { useEffect } from 'react'
import "./Menu.css"
import MenuBar from '../MenuBar/MenuBar'
import axios from 'axios'
import { useData } from '../../context/DataContext'
import MenuCard from '../MenuCard/MenuCard'
import Cart from '../Cart'
const Menu = () => {
    const {data, setData} = useData()
    const {user} = data;

   
      const getData = async () => {
        try{
        const category = []
        const data = await axios.get('http://localhost:9000/inventory/items', {headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`
        }})
        setData({type: "GET_PRODUCTS", products:data.data})
        data.data?.map((ele, i) => {
              category.push(ele.category)
        })
        setData({type:"GET_CATEGORY", category: [...new Set(category)]})
        console.log(data, data.products)
    }catch{
      console.log("Error")
    }
    }
   

    

useEffect(() => {
  getData()
},[])
console.log(data.products)
  return (
    <div className='menu'>
      <MenuBar  category={data.category} setData={setData}/>
      <div className='menu-row'>
      <div className='menu-products-list'>
        <MenuCard menuItem={data.products} categoryName={data.categoryName} userId={user.userId} token={localStorage.getItem("token")} setData={setData}/>
      </div>
      <div className='menu-cart'>
<Cart  />
      </div>
      </div>
 
    </div>
  )
}

export default Menu
