import React from 'react';
import PropTypes from 'prop-types';
import {Link} from 'react-router-dom';

const LibraryItem = (props) => (
    <li>
        {props.item.title}
        <Link to={`/edit/${props.item.token}`}>Edit</Link>
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
