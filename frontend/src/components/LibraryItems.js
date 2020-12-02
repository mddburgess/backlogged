import React, {useEffect} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import LibraryItem from './LibraryItem';
import {listTitles} from '../store/library';
import {Link} from 'react-router-dom';

const LibraryItems = ({items, listTitles}) => {

    useEffect(() => {
        listTitles();
    }, [listTitles]);

    return (
        <ul>
            {items.map(item => <LibraryItem key={item.token} item={item}/>)}
            <li>
                <Link to="/new">
                    Add item
                </Link>
            </li>

        </ul>
    );
};

LibraryItems.propTypes = {
    items: PropTypes.arrayOf(PropTypes.object.isRequired).isRequired,
    listTitles: PropTypes.func.isRequired
};

const mapStateToProps = (state) => ({
    items: state.library.data
});

const mapDispatchToProps = {
    listTitles
};

export default connect(mapStateToProps, mapDispatchToProps)(LibraryItems);
