export interface AddCategoryParam {
  name: string;
}

export interface RenameCategoryParam {
  id: number;
  oldName: string;
  newName: string;
}

export interface DeleteCategoryParam {
  id: number;
  name: string;
}
