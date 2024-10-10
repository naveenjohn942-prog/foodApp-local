import React from 'react'
import "./MenuCard.css"
import { addToCart } from '../../api'
const MenuCard = ({menuItem, categoryName, userId, token, setData}) => {
  
  return (
    
        menuItem?.map((item, i) => {
        console.log(item)
            
            if (!item.category.indexOf(categoryName === 'All' ?"" : categoryName))  {
            return(
                <div className='menucard'>
                <img src={item.imageUrl} alt='' className='menucard-img'   />
                <h3 className='menucard-name'>{item.name}</h3>
                <p className='menucard-price'> <sup>&#8377;</sup>{item.price}</p>
                <button className='menucard-button' onClick={() => addToCart(userId, item.id, token, setData)}>Add to cart</button>
              </div>
            ) } else {
                return 
            }
        })
    

  )
}

export default MenuCard
