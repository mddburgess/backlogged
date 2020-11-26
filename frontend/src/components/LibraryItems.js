import React, {useEffect} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import LibraryItem from './LibraryItem';
import {listTitles} from '../store/library';

const LibraryItems = ({items, listTitles}) => {

    useEffect(() => {
        listTitles();
    }, [listTitles]);

    return (
        <ul>
            {items.map(item => <LibraryItem key={item.token} todo={item.title}/>)}
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
