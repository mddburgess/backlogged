import React, {useEffect} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import TodoItem from './TodoItem';
import {listTodos} from '../store/todos';

const TodoItems = ({todos, listTodos}) => {

    useEffect(() => {
        listTodos();
    }, [listTodos]);

    return (
        <ul>
            {todos.map(todo => <TodoItem key={todo.key} todo={todo.value}/>)}
        </ul>
    );
};

TodoItems.propTypes = {
    todos: PropTypes.arrayOf(PropTypes.shape({
        key: PropTypes.string.isRequired,
        value: PropTypes.string.isRequired
    }).isRequired).isRequired,
    listTodos: PropTypes.func.isRequired
};

const mapStateToProps = (state) => ({
    todos: state.todos
});

const mapDispatchToProps = {
    listTodos
};

export default connect(mapStateToProps, mapDispatchToProps)(TodoItems);
