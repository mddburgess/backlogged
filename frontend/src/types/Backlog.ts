import { Title } from "./Title";

export interface Backlog {
  key?: string;
  title: Title;
}

export interface BacklogProps {
  backlog: Backlog;
}
