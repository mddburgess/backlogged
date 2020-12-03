import React from 'react';
import PropTypes from 'prop-types';
import {Field, FieldArray, Form, Formik} from 'formik';

const LibraryItemForm = ({initialValues, onSubmit}) => (
    <Formik
        initialValues={initialValues}
        onSubmit={onSubmit}
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
);

LibraryItemForm.propTypes = {
    initialValues: PropTypes.object.isRequired,
    onSubmit: PropTypes.func.isRequired
};

export default LibraryItemForm;
