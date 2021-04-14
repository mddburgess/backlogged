export interface Title {
  key?: string;
  name: string;
  copies: Copy[];
}

export interface Copy {
  platform: string;
  service: string;
}

export interface TitleProps {
  title: Title;
}

export interface CopyProps {
  copy: Copy;
}
