import {Field, Form as FormikForm, Formik} from "formik";
import {BacklogItem} from "types/BacklogItem";
import {Button, Form, Modal} from "react-bootstrap";
import API from "api";

type Props = {
    backlogItem?: BacklogItem,
    setBacklogItem: (value?: BacklogItem) => void;
}

const EditBacklogItemModal = ({backlogItem, setBacklogItem}: Props) => {
    const [updateBacklogItem] = API.useUpdateBacklogItemMutation();
    const [deleteBacklogItem] = API.useDeleteBacklogItemMutation();

    const onSubmit = (backlogItem: BacklogItem) => {
        updateBacklogItem(backlogItem);
        setBacklogItem(undefined);
    }

    const onDelete = (backlogItem: BacklogItem) => {
        deleteBacklogItem(backlogItem.id ?? 0);
        setBacklogItem(undefined);
    }

    return (backlogItem ?
            <Modal show={true}>
                <Formik initialValues={backlogItem} onSubmit={onSubmit}>
                    {() => (
                        <FormikForm>
                            <Modal.Header>
                                <Modal.Title>Edit Backlog Item</Modal.Title>
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
                                <Button onClick={() => onDelete(backlogItem)} variant="outline-danger">Delete</Button>
                            </Modal.Footer>
                        </FormikForm>
                    )}
                </Formik>
            </Modal>
            : <></>
    );
}

export default EditBacklogItemModal;
