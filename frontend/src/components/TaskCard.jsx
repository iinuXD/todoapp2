import React, { useState } from 'react';
import AddMoneyModal from './AddMoneyModal';

function TaskCard({ task, displayInfo, onUpdateTaskMoney }) {
  const [showAddMoney, setShowAddMoney] = useState(false);

  return (
    <>
      <div className={`task-card bg-white p-6 rounded-lg shadow-sm border-l-4 border-${displayInfo.color}-500`}>
        <div className="flex items-start justify-between">
          <div className="flex items-start space-x-3">
            <span className="text-2xl">{displayInfo.icon}</span>
            <div className="flex-1">
              <h3 className="text-lg font-semibold text-gray-900">{displayInfo.title}</h3>
              <p className="text-gray-600 mt-1">{displayInfo.description}</p>
              
              {/* Type-specific content */}
              {displayInfo.type === 'DEADLINE' && (
                <div className="mt-3">
                  <span className={`inline-block px-2 py-1 text-xs rounded-full ${
                    displayInfo.color === 'red' ? 'bg-red-100 text-red-800' : 'bg-orange-100 text-orange-800'
                  }`}>
                    Due: {new Date(displayInfo.dueDate).toLocaleDateString()} • {displayInfo.daysLeft}
                  </span>
                </div>
              )}
              
              {displayInfo.type === 'SAVING' && (
                <div className="mt-3">
                  <div className="flex justify-between text-sm text-gray-600 mb-1">
                    <span>${displayInfo.currentAmount.toLocaleString()}</span>
                    <span>${displayInfo.targetAmount.toLocaleString()}</span>
                  </div>
                  <div className="w-full bg-gray-200 rounded-full h-2">
                    <div 
                      className="progress-bar bg-green-600 h-2 rounded-full"
                      style={{ width: `${displayInfo.progress}%` }}
                    ></div>
                  </div>
                  <div className="flex justify-between items-center mt-2">
                    <div className="text-sm text-gray-600">
                      {displayInfo.progress.toFixed(1)}% complete
                    </div>
                    <button
                      onClick={() => setShowAddMoney(true)}
                      className="bg-green-600 text-white px-3 py-1 text-xs rounded-full hover:bg-green-700 transition-colors"
                    >
                      + Add Money
                    </button>
                  </div>
                </div>
              )}
            </div>
          </div>
          
          <div className="flex items-center space-x-2">
            <input
              type="checkbox"
              checked={displayInfo.completed}
              className="w-5 h-5 text-blue-600 border-gray-300 rounded focus:ring-blue-500"
              readOnly
            />
          </div>
        </div>
      </div>

      {/* Add Money Modal */}
      {showAddMoney && (
        <AddMoneyModal
          task={task}
          onClose={() => setShowAddMoney(false)}
          onAddMoney={(amount) => {
            onUpdateTaskMoney(task.id, amount);
            setShowAddMoney(false);
          }}
        />
      )}
    </>
  );
}

export default TaskCard;