import React from 'react';
import PropTypes from 'prop-types';

const TodoItem = (props) => (
    <li>{props.todo}</li>
);

TodoItem.propTypes = {
    todo: PropTypes.string.isRequired
};

export default TodoItem;
