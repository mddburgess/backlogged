import React from 'react';
import PropTypes from 'prop-types';
import {addTitle} from '../store/library';
import {connect} from 'react-redux';
import {Field, FieldArray, Form, Formik} from 'formik';

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
            {({values}) => (
                <Form>
                    <div>
                        <Field name="title" type="text" placeholder="title"/>
                    </div>
                    <FieldArray name="copies">
                        {({push, remove}) => (
                            <div>
                                {values.copies.length > 0 && values.copies.map((copy, index) => (
                                    <div>
                                        <Field
                                            name={`copies[${index}].platform`}
                                            type="text"
                                            placeholder="platform"
                                        />
                                        <Field
                                            name={`copies[${index}].service`}
                                            type="text"
                                            placeholder="service"
                                        />
                                        <button
                                            type="button"
                                            onClick={() => remove(index)}
                                        >
                                            X
                                        </button>
                                    </div>
                                ))}
                                <button
                                    type="button"
                                    onClick={() => push({platform: '', service: ''})}
                                >
                                    Add Copy
                                </button>
                            </div>
                        )}
                    </FieldArray>
                    <div>
                        <button type="submit">Submit</button>
                    </div>
                </Form>
            )}
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
