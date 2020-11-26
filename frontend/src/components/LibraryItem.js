import React from 'react';
import PropTypes from 'prop-types';

const LibraryItem = (props) => (
    <li>{props.todo}</li>
);

LibraryItem.propTypes = {
    todo: PropTypes.string.isRequired
};

export default LibraryItem;
