import React from 'react';
import PropTypes from 'prop-types';

const LibraryItem = (props) => (
    <li>
        {props.item.title}
        <button onClick={() => props.deleteTitle(props.item)}>X</button>
        <ul>
            {props.item.copies.map(copy => <li>{copy.platform} - {copy.service}</li>)}
        </ul>
    </li>
);

LibraryItem.propTypes = {
    item: PropTypes.object.isRequired
};

export default LibraryItem;
