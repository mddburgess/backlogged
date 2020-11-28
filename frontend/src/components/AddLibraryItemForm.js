import React from 'react';
import PropTypes from 'prop-types';
import {addTitle} from '../store/library';
import {connect} from 'react-redux';
import {Field, Form, Formik} from 'formik';

const AddLibraryItemForm = ({addTitle}) => (
    <>
        <h1>Add Library Item</h1>
        <Formik
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
            }}
        >
            <Form>
                <Field name="title" type="text" placeholder="title"/>
                <Field name="copies[0].platform" type="text" placeholder="platform"/>
                <Field name="copies[0].service" type="text" placeholder="service"/>
                <button type="submit">Submit</button>
            </Form>
        </Formik>
    </>
);

AddLibraryItemForm.propTypes = {
    addTitle: PropTypes.func.isRequired
};

const mapDispatchToProps = {
    addTitle
};

export default connect(undefined, mapDispatchToProps)(AddLibraryItemForm);
