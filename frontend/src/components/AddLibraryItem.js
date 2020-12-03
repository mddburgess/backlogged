import React from 'react';
import PropTypes from 'prop-types';
import {addTitle} from '../store/library';
import {connect} from 'react-redux';
import {useHistory} from 'react-router-dom';
import LibraryItemForm from './LibraryItemForm';

const AddLibraryItem = ({addTitle}) => {

    const history = useHistory();

    return (
        <>
            <h1>Add Library Item</h1>
            <LibraryItemForm
                initialValues={{
                    title: '',
                    copies: [
                        {
                            platform: '',
                            service: ''
                        }
                    ]
                }}
                onSubmit={(values) => {
                    addTitle(values);
                    history.push('/');
                }}
            />
        </>
    );
};

AddLibraryItem.propTypes = {
    addTitle: PropTypes.func.isRequired
};

const mapDispatchToProps = {
    addTitle
};

export default connect(undefined, mapDispatchToProps)(AddLibraryItem);
