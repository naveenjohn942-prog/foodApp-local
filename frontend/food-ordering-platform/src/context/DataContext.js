import { Children, createContext, useContext, useReducer } from "react"
import DataReducer from "../reducers/DataReducer"

const initialData = {
    products: [],
category: [],
categoryName: ''
}


const DataContext = createContext(null)

const useData = () => useContext(DataContext)

const DataProvider = ({children}) => {
  const [data, setData] = useReducer(DataReducer, initialData) 
  return (
  <DataContext.Provider value={{data,setData}}>
    {children}
  </DataContext.Provider>)

}


export {DataProvider , useData}