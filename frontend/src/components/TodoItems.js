import React from 'react';
import PropTypes from 'prop-types';
import {v4 as uuid} from 'uuid';
import TodoItem from './TodoItem';

const TodoItems = (props) => (
    <ul>
        {props.todos.map(todo => <TodoItem key={uuid()} todo={todo}/>)}
    </ul>
);

TodoItems.propTypes = {
    todos: PropTypes.arrayOf(PropTypes.string.isRequired).isRequired
};

export default TodoItems;
