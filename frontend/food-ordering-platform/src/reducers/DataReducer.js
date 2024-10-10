const DataReducer = (state, action) => {
    switch (action.type) {
    case "GET_PRODUCTS": 
    return {...state, products: action.products}
    case "GET_CATEGORY":
        return {...state, category: action.category};
    case "GET_CATEGORY_NAME": 
    return {...state, categoryName: action.categoryName};
    case "USER":
        return {...state, user: action.user};
    case "CART": 
    return {...state, cart: action.cart}
    
    }
}

export default DataReducer;