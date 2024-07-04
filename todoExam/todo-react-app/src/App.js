import React, {useState} from 'react';
import Todo from './Todo';

import { Container,Paper,List } from '@mui/material';
import AddTodo from './AddTodo';

function App() {

  const[items, setItems] = useState([
    {
      id:"0",
      title:"Hello World0",
      done:true
    },
    {
      id:"1",
      title:"Hello World1",
      done:true
    },
    {
      id:"2",
      title:"Hello World2",
      done:true
    },
  ]);

  const addItem = (item) => {
    item.id = "ID-" + items.length;
    item.done = false;
    //업데이트
    setItems([...items, item]);
    console.log("item:", items);
  }
const deleteItem = (item) => {
  const newItem = items.filter(e => e.id !== item.id)
  setItems([...newItems]);
}

let todoItems = 
items.length > 0 &&
<Paper style={{margin: 16}}>
  <List>
    {items.map( (item)=> <Todo item={item} key={item.id} deleteItem={deleteItem}/>)}
  </List>
</Paper>

  return (
    <div className="App">
      <Container maxWidth="md">
        <AddTodo/>
        <div className='TodoList'>{todoItems}</div>
      </Container>
      {todoItems}
    </div>
  );
}

export default App;
