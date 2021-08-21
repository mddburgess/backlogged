import {BacklogItem} from "types/BacklogItem";
import {Button, Form, Modal} from "react-bootstrap";
import {Field, Form as FormikForm, Formik} from "formik";
import {useEffect, useState} from "react";

type Props = {
    backlogItem?: Partial<BacklogItem>;
    setBacklogItem: (x?: Partial<BacklogItem>) => void;
    onSave: (x: Partial<BacklogItem>) => void;
}

enum State {
    HIDDEN,
    VISIBLE,
    EXITING,
}

const BacklogItemModal = ({backlogItem, setBacklogItem, onSave}: Props) => {
    const [state, setState] = useState(State.HIDDEN);

    useEffect(() => {
        if (backlogItem !== undefined && state === State.HIDDEN) {
            setState(State.VISIBLE);
        }
    }, [backlogItem, state]);

    const doSave = (backlogItem: Partial<BacklogItem>) => {
        onSave(backlogItem);
        setState(State.EXITING);
    }

    const onHide = () => {
        setState(State.EXITING);
    }

    const onExited = () => {
        setBacklogItem(undefined);
        setState(State.HIDDEN);
    }

    return (
        <Modal show={state === State.VISIBLE} onHide={onHide} onExited={onExited}>
            {backlogItem &&
            <Formik initialValues={backlogItem} onSubmit={doSave}>
                {() => (
                    <FormikForm>
                        <Modal.Header closeButton>
                            <Modal.Title>New Backlog Item</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <Form.Group controlId="name">
                                <Form.Label>Name</Form.Label>
                                <Field name="name" type="text" as={Form.Control}/>
                            </Form.Group>
                            <Form.Group controlId="type">
                                <Form.Label>Type</Form.Label>
                                <Field name="type" type="text" as={Form.Select}>
                                    <option>MOVIE</option>
                                    <option>VIDEO_GAME</option>
                                </Field>
                            </Form.Group>
                            <Form.Group controlId="status">
                                <Form.Label>Status</Form.Label>
                                <Field name="status" type="text" as={Form.Select}>
                                    <option>NEW</option>
                                    <option>ACTIVE</option>
                                    <option>DORMANT</option>
                                    <option>DONE</option>
                                </Field>
                            </Form.Group>
                        </Modal.Body>
                        <Modal.Footer className="justify-content-between">
                            <Button type="submit">Save</Button>
                        </Modal.Footer>
                    </FormikForm>
                )}
            </Formik>}
        </Modal>
    );
};

export default BacklogItemModal;
