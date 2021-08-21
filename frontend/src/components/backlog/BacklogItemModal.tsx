import {Button, Form, Modal} from "react-bootstrap";
import {Field, Form as FormikForm, Formik} from "formik";
import {useEffect, useState} from "react";

type Props<T> = {
    title: string;
    backlogItem?: T;
    setBacklogItem: (x?: T) => void;
    onSave: (x: T) => void;
    onDelete?: (x: T) => void;
}

enum State {
    HIDDEN,
    VISIBLE,
    EXITING,
}

const BacklogItemModal = <T extends object>({
                                                title,
                                                backlogItem,
                                                setBacklogItem,
                                                onSave,
                                                onDelete
                                            }: Props<T>) => {
    const [state, setState] = useState(State.HIDDEN);

    useEffect(() => {
        if (backlogItem !== undefined && state === State.HIDDEN) {
            setState(State.VISIBLE);
        }
    }, [backlogItem, state]);

    const doSave = (backlogItem: T) => {
        onSave(backlogItem);
        setState(State.EXITING);
    }

    const doDelete = (backlogItem: T) => {
        if (onDelete) {
            onDelete(backlogItem);
        }
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
                            <Modal.Title>{title}</Modal.Title>
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
                            {onDelete && <Button onClick={() => doDelete(backlogItem)}
                                                 variant="outline-danger">Delete</Button>}
                        </Modal.Footer>
                    </FormikForm>
                )}
            </Formik>}
        </Modal>
    );
};

export default BacklogItemModal;
