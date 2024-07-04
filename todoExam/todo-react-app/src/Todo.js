import React, {useState} from 'react';
import {Checkbox,IconButton, InputBase, ListItemText, ListItem, ListItemSecondaryAction} from "@mui/material";


const Todo = (props) => {

    console.log(props.item)

    const [item, setItem] = useState(props.item)
    const deleteItem = props.deleteItem; 
    
    const deleteEventHandler = () => {
        deleteItem(item);
    }
    return(
        <ListItem>
            <Checkbox checked={item.id}/>
            <ListItemText>
                <InputBase
                inputProps={{"aria-label": "naked"}}
                type="text"
                id={item.id}
                name={item.id}
                value={item.id}
                multiline={true}
                fullWidth={true}
                />
            </ListItemText>
            <ListItemSecondaryAction>
                <IconButton aria-label="Delete Todo"
                onClick={deleteEventHandler}>
                    
                </IconButton>
            </ListItemSecondaryAction>
        </ListItem>
    );
};

export default Todo;