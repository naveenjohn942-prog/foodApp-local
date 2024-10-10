import React from 'react'
import "./MenuBar.css"
const MenuBar = ({category, setData}) => {
  return (
    <div className='menubar'>
      <div className='menubar-header'>
        <h3>Menu Category</h3>
        <button>view all</button>
      </div>
      <ul className='menubar-list'>
        <button><li className='menubar-items' onClick={() => setData({type:"GET_CATEGORY_NAME", categoryName: "All"})}>All</li></button>
        {
            category?.map((categoryName, i) => {
                return (
                    <button key={i}><li className='menubar-items' onClick={() => setData({type:"GET_CATEGORY_NAME", categoryName: categoryName})}>{categoryName}</li></button>
                )
            })
        }
       
      </ul>
    </div>
  )
}

export default MenuBar
