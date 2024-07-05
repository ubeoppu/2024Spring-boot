import React, {useState} from 'react';
import {Checkbox,IconButton, InputBase, ListItemText, ListItem, ListItemSecondaryAction} from "@mui/material";
import DeleteOutlined from "@mui/icons-material/DeleteOutlined"


const Todo = (props) => {

    console.log(props.item)

    const [item, setItem] = useState(props.item)

    const [readOnly, setReadOnly] = useState(true)

    const deleteItem = props.deleteItem; 
    const editItem = props.editItem;
    
    const deleteEventHandler = () => {
        deleteItem(item);
    }

    const turnOffReadOnly = () => {
        setReadOnly(false);
    }

    const turnOnReadOnly = (e) => {
        if(e.key === 'Enter')
            setReadOnly(true);
        editItem(item);
    }

    const editEventHandler = (e) => {
        // item.title = e.target.value;
        setItem({...item, title:e.target.value});
    }

    const checkboxEventHandler = (e) => {
        item.done = e.target.checked;
        editItem(item);
    }

    return(
        <ListItem>
            <Checkbox checked={item.id} onChange={checkboxEventHandler}/>
            <ListItemText>
                <InputBase
                inputProps={{"aria-label": "naked", readOnly:readOnly}}
                type="text"
                id={item.id}
                name={item.id}
                value={item.title}
                multiline={true}
                fullWidth={true}
                onClick={turnOffReadOnly}
                onKeyDown={turnOnReadOnly}
                onChange={editEventHandler}
                />
            </ListItemText>
            <ListItemSecondaryAction>
                <IconButton aria-label="Delete Todo"
                onClick={deleteEventHandler}>
                <DeleteOutlined/>
                </IconButton>
            </ListItemSecondaryAction>
        </ListItem>
    );
};

export default Todo;