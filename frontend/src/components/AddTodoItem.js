import React, {useState} from 'react';
import PropTypes from 'prop-types';
import * as todosActions from '../store/todos';
import {connect} from 'react-redux';

const AddTodoItem = (props) => {

    const [value, setValue] = useState('');

    const onSubmit = (event) => {
        event.preventDefault();
        props.addTodo(value);
    };

    return (
        <form>
            <input
                type="text"
                placeholder="New item"
                value={value}
                onChange={event => setValue(event.target.value)}
            />
            <button onClick={onSubmit}>Add</button>
        </form>
    );
};

AddTodoItem.propTypes = {
    addTodo: PropTypes.func.isRequired
};

const mapDispatchToProps = {
    addTodo: todosActions.add
};

export default connect(undefined, mapDispatchToProps)(AddTodoItem);
