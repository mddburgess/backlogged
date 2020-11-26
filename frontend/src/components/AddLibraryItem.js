import React, {useState} from 'react';
import PropTypes from 'prop-types';
import {addTitle} from '../store/library';
import {connect} from 'react-redux';

const AddLibraryItem = (props) => {

    const [value, setValue] = useState('');

    const onSubmit = (event) => {
        event.preventDefault();
        props.addTitle(value);
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

AddLibraryItem.propTypes = {
    addTitle: PropTypes.func.isRequired
};

const mapDispatchToProps = {
    addTitle
};

export default connect(undefined, mapDispatchToProps)(AddLibraryItem);
