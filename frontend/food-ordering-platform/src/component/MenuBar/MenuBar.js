import React from 'react';
import './MenuBar.css';

const MenuBar = ({ category, setData, setSearchTerm }) => {
  return (
    <div className='menubar'>
      <div className='menubar-header'>
        <h3>Menu Category</h3>
        <button>view all</button>
      </div>

      {/* Search input field */}
      

      <ul className='menubar-list'>
        <button>
          <li
            className='menubar-items'
            onClick={() => setData({ type: 'GET_CATEGORY_NAME', categoryName: 'All' })}
          >
            All
          </li>
        </button>
        {category?.map((categoryName, i) => (
          <button key={i}>
            <li
              className='menubar-items'
              onClick={() => setData({ type: 'GET_CATEGORY_NAME', categoryName })}
            >
              {categoryName}
            </li>
          </button>
        ))}
        <input
        type="text"
        placeholder="Search items..."
        className="menubar-search"
        onChange={(e) => setSearchTerm(e.target.value)}
      />
      </ul>
    </div>
  );
};

export default MenuBar;
