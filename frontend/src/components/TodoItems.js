import React from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import TodoItem from './TodoItem';

const TodoItems = (props) => (
    <ul>
        {props.todos.map(todo => <TodoItem key={todo.key} todo={todo.value}/>)}
    </ul>
);

TodoItems.propTypes = {
    todos: PropTypes.arrayOf(PropTypes.shape({
        key: PropTypes.string.isRequired,
        value: PropTypes.string.isRequired
    }).isRequired).isRequired
};

const mapStateToProps = (state) => ({
    todos: state.todos
});

export default connect(mapStateToProps)(TodoItems);
