import React from 'react';
import './MenuBar.css';

const MenuBar = ({ category, setCategoryName, setSearchTerm }) => {
  return (
    <div className='menubar'>
      <div className='menubar-header'>
        <h3>Menu Category</h3>
        <button onClick={() => setCategoryName('All')}>View All</button>
      </div>

      {/* Search input field */}
      <ul className='menubar-list'>
        <button onClick={() => setCategoryName('All')}>
          <li className='menubar-items'>All</li>
        </button>
        {category?.map((categoryName, i) => (
          <button key={i} onClick={() => setCategoryName(categoryName)}>
            <li className='menubar-items'>{categoryName}</li>
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
