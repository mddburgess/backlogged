import React from 'react';
import PropTypes from 'prop-types';
import {Field, FieldArray, Form as FormikForm, Formik} from 'formik';
import {Button, Col, Form} from 'react-bootstrap';

const LibraryItemForm = ({initialValues, onSubmit}) => (
    <Formik
        initialValues={initialValues}
        onSubmit={onSubmit}
    >
        {({values}) => (
            <FormikForm>
                <Form.Group controlId="name">
                    <Form.Label>Name</Form.Label>
                    <Field
                        name="name"
                        type="text"
                        as={Form.Control}
                    />
                </Form.Group>
                <FieldArray name="copies">
                    {({push, remove}) => (
                        <div>
                            {values.copies.length > 0 && values.copies.map((copy, index) => (
                                <Form.Row>
                                    <Col>
                                        <Form.Group controlId={`copies[${index}].platform`}>
                                            <Form.Label>Platform</Form.Label>
                                            <Field
                                                name={`copies[${index}].platform`}
                                                as={Form.Control}
                                            />
                                        </Form.Group>
                                    </Col>
                                    <Col>
                                        <Form.Group controlId={`copies[${index}].service`}>
                                            <Form.Label>Service</Form.Label>
                                            <Field
                                                name={`copies[${index}].service`}
                                                as={Form.Control}
                                            />
                                        </Form.Group>
                                    </Col>
                                    <Col xs="auto">
                                        <Button onClick={() => remove(index)}
                                        >
                                            X
                                        </Button>
                                    </Col>
                                </Form.Row>
                            ))}
                            <Button
                                onClick={() => push({platform: '', service: ''})}
                            >
                                Add Copy
                            </Button>
                        </div>
                    )}
                </FieldArray>
                <div>
                    <Button type="submit">Submit</Button>
                </div>
            </FormikForm>
        )}
    </Formik>
);

LibraryItemForm.propTypes = {
    initialValues: PropTypes.object.isRequired,
    onSubmit: PropTypes.func.isRequired
};

export default LibraryItemForm;
