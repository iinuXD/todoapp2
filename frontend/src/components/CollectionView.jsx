import React, { useState, useEffect } from 'react';
import apiService from '../services/api';
import TaskCard from './TaskCard';
import TaskFormModal from './TaskFormModal';
import { TaskFactory } from '../models/TaskFactory';

function CollectionView({ collection, onBack, onLogout, onUpdateCollection }) {
  const [tasks, setTasks] = useState([]);
  const [showTaskForm, setShowTaskForm] = useState(false);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadTasks();
  }, [collection.id]);

  const loadTasks = async () => {
    try {
      setLoading(true);
      const response = await apiService.getTasksByCollection(collection.id);
      if (response.success) {
        setTasks(response.data);
      }
    } catch (error) {
      console.error('Error loading tasks:', error);
    } finally {
      setLoading(false);
    }
  };

  const addTaskToCollection = async (taskData) => {
    try {
      const response = await apiService.createTask(collection.id, taskData);
      if (response.success) {
        setTasks([...tasks, response.data]);
        setShowTaskForm(false);
      }
    } catch (error) {
      console.error('Error creating task:', error);
    }
  };

  const updateTaskMoney = async (taskId, amount) => {
    try {
      const response = await apiService.addMoneyToTask(taskId, amount);
      if (response.success) {
        const updatedTasks = tasks.map(task => 
          task.id === taskId 
            ? { ...task, currentAmount: (task.currentAmount || 0) + amount }
            : task
        );
        setTasks(updatedTasks);
      }
    } catch (error) {
      console.error('Error adding money to task:', error);
    }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <header className="bg-white shadow-sm border-b">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex items-center justify-between py-4">
            <div className="flex items-center">
              <button
                onClick={onBack}
                className="mr-4 text-gray-600 hover:text-gray-900"
              >
                ← Back
              </button>
              <div>
                <h1 className="text-2xl font-bold text-gray-900">{collection.name}</h1>
                <p className="text-gray-600">{collection.description}</p>
              </div>
            </div>
            <div className="flex items-center space-x-4">
              <button
                onClick={() => setShowTaskForm(true)}
                className="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700"
              >
                + Add Task
              </button>
              <button
                onClick={onLogout}
                className="bg-gray-600 text-white px-4 py-2 rounded-lg hover:bg-gray-700"
              >
                Sign Out
              </button>
            </div>
          </div>
        </div>
      </header>

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        {loading ? (
          <div className="text-center py-12">
            <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600 mx-auto mb-4"></div>
            <p className="text-gray-600">Loading tasks...</p>
          </div>
        ) : tasks.length === 0 ? (
          <div className="text-center py-12">
            <div className="text-6xl mb-4">✅</div>
            <h3 className="text-lg font-medium text-gray-900 mb-2">No tasks yet</h3>
            <p className="text-gray-600">Add your first task to get started</p>
          </div>
        ) : (
          <div className="space-y-4">
            {tasks.map(taskData => {
              const task = TaskFactory.createTask(taskData.type, taskData);
              const displayInfo = task.getDisplayInfo();
              
              return (
                <TaskCard 
                  key={task.id} 
                  task={task} 
                  displayInfo={displayInfo}
                  onUpdateTaskMoney={updateTaskMoney}
                />
              );
            })}
          </div>
        )}
      </div>

      {showTaskForm && (
        <TaskFormModal
          onClose={() => setShowTaskForm(false)}
          onSubmit={addTaskToCollection}
        />
      )}
    </div>
  );
}

export default CollectionView;